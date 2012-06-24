macro assign:"assign.l"
macro sub:"subtracao.l"
macro maior:"maior.l"

if x1 != 0 goto teste
goto fim

[teste] if x2 != 0 goto inicio
goto infinito

[inicio] z1=assign(x1)

[cont] z1=sub(z1,x2)
y = y + 1

z3 = maior(z1,x2)
if z3 != 0 goto cont
goto fim

[infinito] goto infinito