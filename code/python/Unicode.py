f = open("a.txt",mode="r+")
f.write("[")
for i in range(0,65535):
    he = hex(i)
    sss= '\\u' + he[2:len(he)].zfill(4)
    print(sss)
    f.write("\'")
    f.write(sss)
    f.write("\'")
    f.write(",")
f.write("]")
    