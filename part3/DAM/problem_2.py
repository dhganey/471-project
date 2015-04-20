import sys
from random import randint

Catk = sys.argv[1]
Cdef = 100
Xatk = randint(3,100)
Xdef = randint(3,100)
Patk = 0
Pdef = 0
Batk = 10000

Xatk = int(sys.argv[2])
Xdef = int(sys.argv[3])

XatkV = []
XdefV = []


flag = 0
for i in xrange(Xatk):
    XatkV.append(0)

for i in xrange(0, Xatk):
	m = randint(1,100)

	for j in xrange(0, i):
		if m == XatkV[j]:
			# then we have to find a new random number
			flag = 1
			#print "{}    {}".format(i, j)
			while flag == 1:
				l = randint(1,100)
				track = 0
				for k in xrange(0, i):
					if l == XatkV[k]:
						track = 1

				if track == 0:
					m = l
					flag = 0

	XatkV[i] = m
	#flag = 0


flag = 0
for i in xrange(Xdef):
    XdefV.append(0)

for i in xrange(0, Xdef):
	m = randint(1,100)

	for j in xrange(0, i):
		if m == XdefV[j]:
			# then we have to find a new random number
			flag = 1
			#print "{}    {}".format(i, j)
			while flag == 1:
				l = randint(1,100)
				track = 0
				for k in xrange(0, i):
					if l == XdefV[k]:
						track = 1

				if track == 0:
					m = l
					flag = 0

	XdefV[i] = m

for i in xrange(0, Xdef):
	for j in xrange(0, Xdef):
		if XdefV[j] == XdefV[i] and i != j:
			print "{}    {}".format(i, j)

for i in xrange(0, Xatk):
	for j in xrange(0, Xatk):
		if XatkV[j] == XatkV[i] and i != j:
			print "{}    {}".format(i, j)


print "\nNumber of attacks {}".format(Xatk)
print "Number of defends {}\n".format(Xdef)

 
print "The Defender defends against attacks:"
for i in xrange(0, Xdef):
	print "{} ".format(XdefV[i]), 
print 

print 
print "The Attacker attacks using:"
for i in xrange(0, Xatk):
	print "{} ".format(XatkV[i]), 
print 
print

successfulD = 0
whereDs = []
count = 0

for i in xrange(Xatk):
    whereDs.append(0)

for i in xrange(0, Xdef):
	for j in xrange(0, Xatk):
		if XdefV[i] == XatkV[j]:
			successfulD = 1
			whereDs[count] = XdefV[i]
			#print "Successful Defense @ {}".format(XdefV[i])
			count += 1

if successfulD == 1:
	# this is when defense blocks the attack

	print "Successful Defense at:"
	for i in xrange(0, count):
		print whereDs[i],

	print "\n"
	print "Payoff:\n",


	print "Attacker: ",
	print - (int(Catk) * Xatk)
	print "Defender: ",
	print - (Cdef * Xdef)



if successfulD == 0:
	print "Payoff: "
	print "Attacker:",
	Patk = Batk - (int(Catk) * Xatk)
	print Patk
	print "Defender:",
	Pdef = -Batk - (Cdef * Xdef)
	print Pdef




