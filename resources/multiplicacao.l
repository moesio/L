macro add:"soma.l"
macro assign:"assign.l"

if x1 != 0 goto teste
y=assign(x1)
goto fim

[teste] if x2 != 0 goto inicio
y=assign(x2)
goto fim

[inicio] y = assign(x1)
x2 = x2 - 1
[times] y = add(y,x1)
x2 = x2 - 1
if x2 != 0 goto times