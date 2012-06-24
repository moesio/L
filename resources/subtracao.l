macro assign:"assign.l"
y=assign(x1)			
[inicio] if x2 != 0 goto sub
z = z + 1
if z != 0 goto fim  
[sub] y = y - 1
x2 = x2 - 1
if x2 != 0 goto inicio