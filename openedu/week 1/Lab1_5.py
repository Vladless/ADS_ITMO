input_file = open('input.txt', 'r')
for number, line in enumerate(input_file.readlines()):
    if number == 0:
        lenght = int(line)
        continue
    elements = [int(num) for num in line.split(' ')]
    
output_file = open('output.txt','w')
for i in range(int(lenght)-1):
	min_el = min(elements[i:])
	position = elements[i:].index(min_el) + i
	if min_el < elements[i]:
		elements[i], elements[position] = min_el, elements[i]
		output_file.write(f'Swap elements at indices {i+1} and {position+1}.\n')

output_file.write('No more swaps needed.\n')
string = ''			
for i in range(int(lenght)):
	string += str(elements[i]) + ' '
output_file.write(string)



