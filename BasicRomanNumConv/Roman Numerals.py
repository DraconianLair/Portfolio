# Andrew Smith
# Program 4

# read roman.txt
inFile = open('roman.txt','r')

# read first line
line = inFile.readline()

# continue until empty
while (line != ''):
    
    # create running total/reset for each line
    total = 0

    # check each character for roman values
    for x in range(len(line)):
        if line[x] == 'M':
            total = total + 1000
        if line[x] == 'D':
            total = total + 500
        if line[x] == 'C':
            total = total + 100
        if line[x] == 'L':
            total = total + 50
        if line[x] == 'X':
            total = total + 10
        if line[x] == 'V':
            total = total + 5
        if line[x] == 'I':
            total = total + 1
            
    # print the values with context
    print('For the Roman value:',line.strip())
    print('The Arabic value is:',total,'\n')

    # read next line
    line = inFile.readline()

# close file
inFile.close()
