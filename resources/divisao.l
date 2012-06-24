macro assign:"assign.l"
macro sub:"subtracao.l"
macro maior:"maior.l"

z1=maior(x2,x1)
if z1 != 0 goto fim
goto run

[run] if x1 != 0 goto teste
goto fim

[teste] if x2 != 0 goto inicio
goto infinito

[inicio] z1=assign(x1)

[cont] z1=sub(z1,x2)
y = y + 1

z3 = maior(x2,z1)
if z3 != 0 goto fim
goto cont

[infinito] goto infinito