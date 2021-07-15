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
            Simbolo simbFila = filaInFixa.poll();//desinfilera e retorna o elemento para simbfila
            if (simbFila.isOperando()) {//se o simbfila é operando...
                filaPosFixa.offer(simbFila);//filaPosfixa recebe o operando
            }
            else if (simbFila.isAbreParenteses()) {//se o simbfila é abreParenteses...
                pilhaConv.push(simbFila);//pilhaConv recebe o abreParenteses
            }
            else if (simbFila.isOperador()) {//se o simbfila é operador...
                while ((!pilhaConv.isEmpty()) &&//verifica a prioridade do topo da pilha em relação ao simbfila
                        (pilhaConv.peek().verificarPrioridaade() >= simbFila.verificarPrioridaade())) {
                    Simbolo simbPilha = pilhaConv.pop();//cria o simbPilha para receber o simbfila
                    filaPosFixa.offer(simbPilha);
                }
                pilhaConv.push(simbFila);//empilha o simbfila
            }
            else if (simbFila.isFechaParenteses()){
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
//************************************************************************
        Stack<Simbolo> pilhaCalc = new Stack();//cria uma pilha vazia

        while (! filaPosFixa.isEmpty()){
            Simbolo simbFila = filaPosFixa.poll();
            if (simbFila.isOperando()){
                pilhaCalc.push(simbFila);
            }
            else if (simbFila.isOperador()){
                Simbolo operandoA = pilhaCalc.pop();
                Simbolo operandoB = pilhaCalc.pop();
                Simbolo resultado = operandoB= simbFila= operandoA;
                pilhaCalc.push(resultado);
            }
        }
        System.out.println(pilhaCalc);
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