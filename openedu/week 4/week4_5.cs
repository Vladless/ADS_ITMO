using System;
using System.Collections.Generic;
using System.IO;
using System.Text;

namespace Week4
{
    class Lab4_5
    {
        public static void Main(string[] args)
        {
            StreamWriter sw = new StreamWriter("output.txt");
            Console.OutputEncoding = Encoding.ASCII;
            Console.SetOut(sw);
            string[] stdin = File.ReadAllLines("input.txt");
            Quack quack = new Quack(stdin);
            quack.Run();
            sw.Dispose();
        }
    }
    class Quack
    {
        private static ushort[] registers = new ushort[26];
        private static int position = 0;
        private static Dictionary<string, int> labels = new Dictionary<string, int>();
        private static string[] code;
        private static Queue<ushort> queue = new Queue<ushort>();

        public Quack(string[] input)
        {
            code = input;
            RegisterLabels();
        }

        public void Run()
        {
            for (position = 0; position < code.Length; position++)
            {
                switch (code[position][0])
                {
                    case '+':
                        queue.Enqueue((ushort)((queue.Dequeue() + queue.Dequeue()) % 65536));
                        break;
                    case '-':
                        queue.Enqueue((ushort)((queue.Dequeue() - queue.Dequeue()) % 65536));
                        break;
                    case '*':
                        queue.Enqueue((ushort)(queue.Dequeue() * queue.Dequeue()));
                        break;
                    case '/':
                        queue.Enqueue((ushort)(queue.Dequeue() / queue.Dequeue()));
                        break;
                    case '%':
                        queue.Enqueue((ushort)(queue.Dequeue() % queue.Dequeue()));
                        break;
                    case '>':
                        GetRegister(code[position][1]);
                        break;
                    case '<':
                        SetRegister(code[position][1]);
                        break;
                    case 'P':
                        if (code[position].Length == 1)
                            PrintValueFromStack();
                        else
                            PrintValueFromRegister(code[position][1]);
                        break;
                    case 'C':
                        if (code[position].Length == 1)
                            PrintCharFromStack();
                        else
                            PrintCharFromRegister(code[position][1]);
                        break;
                    case ':': break;
                        GoTo(position);
                        break;
                    case 'Z':
                        GoToIfZeroEqual(position);
                        break;
                    case 'E':
                        GoToIfEquals(position);
                        break;
                    case 'G':
                        GoToIfMoreThan(position);
                        break;
                    case 'Q':
                        Exit();
                        break;
                    default: queue.Enqueue(ushort.Parse(code[position])); break;
                }
            }
        }
        private void RegisterLabels()
        {
            for (int i = 0; i < code.Length; i++)
                if (code[i][0] == ':')
                    labels.Add(code[i].Remove(0, 1), i);
        }

        private void GetRegister(char register)
        {
            registers[register- 'a'] = queue.Dequeue();
        }

        private void SetRegister(char register)
        {
            queue.Enqueue(registers[register - 'a']);
        }

        private void PrintValueFromStack()
        {
            ushort a = queue.Dequeue();
            Console.WriteLine(a);
        }

        private void PrintValueFromRegister(char register)
        {
            Console.WriteLine(registers[register - 'a']);
        }

        private void PrintCharFromStack()
        {
            ushort a = queue.Dequeue();
            Console.Write((char)(a % 256));
        }

        private void PrintCharFromRegister(char register)
        {
            Console.Write((char)(registers[register - 'a'] % 256));
        }

        private void GoTo(int index)
        {
            position = labels[code[index].Remove(0, 1)];
        }

        private void GoToIfZeroEqual(int index)
        {
            if (registers[code[index][1]  - 'a'] == 0)
                position = labels[code[index].Remove(0, 2)];
        }

        private void GoToIfEquals(int index)
        {
            if (registers[code[index][1] - 'a'] == registers[code[index][2] - 'a'])
                position = labels[code[index].Remove(0, 3)];
        }

        private void GoToIfMoreThan(int index)
        {
            if (registers[code[index][1]  - 'a'] > registers[code[index][2] - 'a'])
                position = labels[code[index].Remove(0, 3)];
        }

        private void Exit()
        {
            position = int.MaxValue;
        }
    }
}