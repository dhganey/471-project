import sys
Catk = sys.argv[1]
Catk = int(Catk)

Patk = 0
Pdef = 0

Batk = 10000

xatk = 10
xdef = 22


Cdef = 100

odds_success_a = 0

print "\nNumber of attacks: {}".format(xatk)
print "Number of defends: {}".format(xdef)


temp = 0.0
ans = 0.0
i = 1
ans = ( 100.0 - xatk - i ) / (100.0 - i)
i = i + 1
prob = 0.0
if xdef < 100 and xatk < 99:

	while (i <= xdef):

		temp = ( 100.0 - xatk - i ) / (100.0 - i)
		ans = temp*ans

		#if i == 1:
			#print temp

		i = i + 1
	
	prob = 1 - ans
	print "\nLikelyhood of successful defense: {:.2%}".format(prob)


sa = float(Batk - xatk * Catk)
sa = sa * ans


sd = float(-xatk * Catk)
sd = sd * prob

ma = float(-Batk - xdef * Cdef)
ma = ma * ans

md = float(-xdef * Cdef)
md = md * prob


print "\nAveraged out attacker: ${0:.2f}".format(sa + sd)
print "Averaged out defender: ${0:.2f}".format(ma + md)

print "\nPayoff: "
if xdef != 100:
	print "Successful attack:"
	print "Attacker: {}".format(Batk - xatk * Catk)
	print "Defender: {}\n".format(-Batk - xdef * Cdef)
else:
	print "Successful attack not possible\n"

print "Successful defense:"
print "Attacker: {}".format(-xatk * Catk)
print "Defender: {}".format(-xdef * Cdef)
