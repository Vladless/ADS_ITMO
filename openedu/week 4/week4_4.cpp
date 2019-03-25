#include "edx-io.hpp"
#include "stdafx.h"
#include <iostream>
#include <conio.h>
#include <cstdlib>
#include <fstream>
#include <algorithm>
using namespace std;

int main() {
 
	string sign;
	//указатели на хвост и голову очереди
	int tail = 0;
	int	head = 0;
	//счетчики добавления и удаления
	int	counterPlus = 0;
	int	counterMinus = 0;
	
	int	len = 0;
	int	value = 0;
	io >> len;
	int* queue = new int[len];
	for (int i = 0; i < len; i++) {
		io >> sign;
		if (sign == "+") {
			if (++tail == len)
				tail = 0;
			io >> value;
			queue[tail] = value;
			counterPlus++;
		}
		if (sign == "-") {
			if (++head == len) {
				head = 0;
			}
			counterMinus++;
		}
		if (sign == "?") {
			//находим длину дейсвительной очереди
			int tmplen = (counterPlus + 1) - (counterMinus + 1);
			int *tmpArray = new int[tmplen];
			int tmp = counterMinus + 1;
			//копируем дейсвительную последовательность во временный массив
			for (int i = 0; i < counterPlus+1; i++) {
				tmpArray[i] = queue[tmp++];
			}
			//сортируем массив и выводим минмальны элемент массива,
			//а по совместительству и очереди
			sort(tmpArray, tmpArray + tmplen);
			io << tmpArray[0] << "\n";
		}
	}
	return 0;
}
