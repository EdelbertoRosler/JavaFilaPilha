package calculadoraDeExpresao;

/**
 *
 * @author edelb
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Atividade4 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String exp = scan.nextLine();
        String[] Simbolos = exp.split(" ");


        Stack<String> pilhaConv = new Stack();//cria uma pilha vazia
        Queue<String> filaInFixa = new LinkedList(); //cria uma fila vazia
        Queue<String> filaPosFixa = new LinkedList(); //cria uma fila vazia

        for(String simb : Simbolos) {
            filaInFixa.offer(simb);

            while (! filaInFixa.isEmpty()){
                filaInFixa.poll();
                if(isOperando()){
                    filaPosFixa.offer(simb);
                }
                else if (isAbreParenteses()){
                    pilhaConv.push(simb);
                }
                else if (isOperador()){
                    while ((! pilhaConv.isEmpty()) && (pilhaConv.peek().prioridadeOperador()) => simb.filaInFixa){
                        filaPosFixa.offer(pilhaConv.pop());
                    }
                    pilhaConv.push(simb);
                }
                else if (teste.isFechaParenteses()){
                    while (! pilhaConv.peek() isAbreParenteses()){
                        filaPosFixa.offer(pilhaConv.pop(simb));
                    }
                    pilhaConv.push(simb);
                }
            }
            // while (! pilhaConv.isEmpty()){
            //    filaPosFixa.offer(pilhaConv.pop(simb));
        }
    }
    //System.out.println(filaPosFixa);
}
}
/*
Stack<Character> minhaPilha = new Stack();// cria uma pilha
pilha push('a'); //insere um elemento na pilha
pilha push('b');
pilha push('c');

System.out.print(minhaPilha);
System.out.print(minhaPilha.pop()); //desempilha e retorna o elemento
while(! minhaPilha.isEmpty()){ //enquanto a pilha n está vazia, vai desempilhando
    System.out.println(pilha.pop());
}
************************************
Queue<Character> minhaFila = new LinkedList(); //cria uma fila
minhafila.offer('a'); //insere um elemento na fila
minhafila.offer('b');
minhafila.offer('c');
System.out.println(minhaFila);
System.out.println(minhaFila.poll); desenfilera e retorna o elemento
While(! mihaFila.isEmpty()){
    System.out.println(minhaFila.poll());
*/