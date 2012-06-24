if x1 != 0 goto teste
goto par

[teste] x1 = x1 - 1
if x1 != 0 goto cont
goto impar
[cont] x1 = x1 - 1
if x1 != 0 goto teste
goto par

[par] y = y + 1