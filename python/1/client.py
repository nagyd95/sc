import sys
import csv
import subprocess
import json
import threading
import platform
from datetime import date
from time import sleep

if len (sys.argv) != 2 :
    print ("Usage: python ex.py ")
    sys.exit (1)
def track(cimek):
    process=[]
    jsonTarget=[]
    for i in cimek:
        cim=i.split(',')[1].split('\n')[0]
        #print("trace:",cim)
        p=subprocess.Popen(['tracert', '-h', '30', cim], stdout=subprocess.PIPE)
        process.append(p)
    for j in process:
        j.wait()
        jsonTarget.append(j.communicate()[0].decode())
        #print(jsonTarget)
    return jsonTarget
  
def ping(cimek):
    process=[]
    jsonTarget=[]
    for i in cimek:
        cim=i.split(',')[1].split('\n')[0]
        #print("ping:",cim)
        p=subprocess.Popen(['ping', '-n', '10', cim], stdout=subprocess.PIPE)
        process.append(p)
    for j in process:
        j.wait()
        jsonTarget.append(j.communicate()[0].decode())
    return jsonTarget
 
def tail(file, n=10, bs=1024):
    f = open(file,encoding="ISO-8859-1")
    f.seek(0,2)
    l = 1-f.read(1).count('\n')
    B = f.tell()
    while n >= l and B > 0:
            block = min(bs, B)
            B -= block
            f.seek(B, 0)
            l += f.read(block).count('\n')
    f.seek(B, 0)
    l = min(l,n)
    lines = f.readlines()[-l:]
    return lines

with open(sys.argv[1],encoding="ISO-8859-1") as myfile:
    head = [next(myfile) for x in range(10)]
    tal=(tail(sys.argv[1]))
    for y in tal:
      head.append(y)

    ping_resulst_target=ping(head)
    ping_result={
    'date':str(date.today().strftime("%Y%m%d")),
    'system':str(platform.system()),
    'pings': []
    }
    for i in range(len(ping_resulst_target)):
        ping_result["pings"].append({ "target": head[i].split(',')[1].split('\n')[0] , "output": str(ping_resulst_target[i])})

    track_resulst_target=track(head)
    track_result={
    'date':str(date.today().strftime("%Y%m%d")),
    'system':str(platform.system()),
    'traces': []
    }
    for i in range(len(track_resulst_target)):
        track_result["traces"].append({ "target": head[i].split(',')[1].split('\n')[0] , "output": str(track_resulst_target[i])})
    
    with open('traceroute.json','w') as output:
        json.dump(track_result,output,indent=4,ensure_ascii=True)
    output.close()
    with open('ping.json','w') as output:
      json.dump(ping_result,output,indent=4,ensure_ascii=True)
    output.close()
    
    
    