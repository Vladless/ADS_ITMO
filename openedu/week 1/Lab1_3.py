input_file = open('input.txt', "r")
for number, line in enumerate(input_file.readlines()):
    if number == 0:
        lenght = int(line)
        continue
    elements = [int(num) for num in line.split(' ')]
position = []
for i in range(lenght):
    j = i - 1
    key = elements[i]
    while elements[j] > key and j >= 0:
        elements[j + 1] = elements[j]
        j -= 1
    elements[j + 1] = key
    position.append(j+2)
    output_file = open("output.txt", "w")
    output_file.write(' '.join(str(pos) for pos in position) + '\n')
    output_file.write(' '.join(str(els) for els in elements))





 



