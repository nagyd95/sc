import sys
import json

def mehet(foglaltUtak,x):
  for i in foglaltUtak:
    for a in range(len(x)-1):
      if(i.split(',')[0]==x[a] and i.split(',')[1]==x[a+1]):
        return False
  return True

def simulation(osszesAdat):
  index=1
  futoKapcsolatok=[]
  foglaltUtak=[]
  duration=osszesAdat["simulation"]["duration"]
  for ido in range(1,duration+1):
    #print(ido," :",futoKapcsolatok,"  ,  ",foglaltUtak)
    for i in osszesAdat["simulation"]["demands"]:
      for ut in foglaltUtak:
        utak=ut.split(',')
        if(int(utak[2])==ido):
          foglaltUtak.remove(ut)
      for kapcs in futoKapcsolatok:
        if(kapcs[len(kapcs)-1]==ido):
          print(str(index)+". igény felszabadítás:",kapcs[0],"<->",kapcs[len(kapcs)-2],"st:"+str(ido))
          index+=1
          futoKapcsolatok.remove(kapcs)
      startIdo=i["start-time"]
      endIdo=i["end-time"]
      if(startIdo==ido):
        startPont=i["end-points"][0]
        endPont=i["end-points"][1]
        demand=i["demand"]
        for x in osszesAdat["possible-circuits"]:
          if(x[0]==startPont and x[len(x)-1]==endPont):
            if(mehet(foglaltUtak,x)):
              for y in range(0,len(x)-1):
                foglaltUtak.append(x[y]+','+x[y+1]+','+str(endIdo)) 
              x.append(endIdo)
              print(str(index)+". igény foglalás:",x[0],"<->",x[len(x)-2],"st:",str(ido)+"-sikeres")
              index+=1
              futoKapcsolatok.append(x)
              break
            else:
              print(str(index)+". igény foglalás:",x[0],"<->",x[len(x)-1],"st:",str(ido)+"-sikertelen")
              index+=1
      #print (startIdo,endIdo,ido)
      #if(endIdo==ido):
       # print(x[len(x)-1])
       # print(futoKapcsolatok)
        
  



if len (sys.argv) != 2 :
    print ("Usage: python ex.py ")
    sys.exit (1)

with open(sys.argv[1]) as json_file:
    data = json.load(json_file)
    simulation(data)

