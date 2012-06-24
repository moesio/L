macro assign:"assign.l"
z1 = assign(x1)
z2 = assign(x2)
if z1 != 0 goto vai
if z2 != 0 goto somax
goto fim
[vai] y = y + 1
z1 = z1 - 1
if z1 != 0 goto vai
[somax]	if z2 != 0 goto soma
goto fim
[soma] y = y + 1
z2 = z2 - 1
if z2 != 0 goto somax
