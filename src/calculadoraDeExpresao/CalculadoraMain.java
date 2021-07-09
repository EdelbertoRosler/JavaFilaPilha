package calculadoraDeExpresao;

/**
 *
 * @author edelb
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class CalculadoraMain {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String exp = scan.nextLine();
        String[] Simbolos = exp.split(" ");


        Stack<Simbolo> pilhaConv = new Stack();//cria uma pilha vazia
        Queue<Simbolo> filaInFixa = new LinkedList(); //cria uma fila vazia
        Queue<Simbolo> filaPosFixa = new LinkedList(); //cria uma fila vazia

        for(String simb : Simbolos) {
            filaInFixa.offer(new Simbolo(simb));
        }
        while (! filaInFixa.isEmpty()) {
            Simbolo simbfila = filaInFixa.poll();//desinfilera e retorna o elemento para simbfila
            if (simbfila.isOperando()) {//se o simbfila é operando...
                filaPosFixa.offer(simbfila);//filaPosfixa recebe o operando
            }
            else if (simbfila.isAbreParenteses()) {//se o simbfila é abreParenteses...
                pilhaConv.push(simbfila);//pilhaConv recebe o abreParenteses
            }
            else if (simbfila.isOperador()) {//se o simbfila é operador...
                while ((!pilhaConv.isEmpty()) &&//verifica a prioridade do topo da pilha em relação ao simbfila
                        (pilhaConv.peek().verificarPrioridaade() >= simbfila.verificarPrioridaade())) {
                    Simbolo simbPilha = pilhaConv.pop();//cria o simbPilha para receber o simbfila
                    filaPosFixa.offer(simbPilha);
                }
                pilhaConv.push(simbfila);//empilha o simbfila
            }
            else if (simbfila.isFechaParenteses()){
                while (! pilhaConv.peek().isFechaParenteses()){
                    Simbolo simbPilha = pilhaConv.pop();
                    filaPosFixa.offer(simbPilha);
                }
                pilhaConv.pop();
            }
        }
        while (! pilhaConv.isEmpty()){
            Simbolo simbPilha = pilhaConv.pop();
            filaPosFixa.offer(simbPilha);
        }
        System.out.println(filaPosFixa);
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