if x1 != 0 goto teste
goto fim

[teste] if x2 != 0 goto inicio
goto maior
[inicio] x1 = x1 - 1
x2 = x2 - 1
if x1 != 0 goto teste
if x2 != 0 goto fim

[maior] y = y + 1