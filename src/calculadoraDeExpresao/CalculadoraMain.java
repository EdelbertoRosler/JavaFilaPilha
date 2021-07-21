package calculadoraDeExpresao;
/**
 * Estrutura de dados filas, pilhas
 * Baseado na aula do prof. Felipe Sampaio - IFRS
 * @author edelberto Hermann Rösler
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
            else if (simbFila.isFechaParenteses()){ // caso o simbfila for fecha parênteses
                while (! pilhaConv.peek().isAbreParenteses()){
                    Simbolo simbPilha = pilhaConv.pop();
                    filaPosFixa.offer(simbPilha);
                }
                pilhaConv.pop();
            }
        }
        while (! pilhaConv.isEmpty()){//enquanto a pilha não está vazia
            Simbolo simbPilha = pilhaConv.pop();//empilha no simbPila
            filaPosFixa.offer(simbPilha);//enfileira na filaPosFixa os elementos que estão na simbPilha
        }
        System.out.println(filaPosFixa);//imprime a fila com todos o elementos

//*************************Cálculo***********************************************
        Stack<Integer> pilhaCalc = new Stack();//cria uma pilha vazia

        while (! filaPosFixa.isEmpty()){
            Simbolo simbFila = filaPosFixa.poll();
            if (simbFila.isOperando()){//testa se o elemento é operando
                pilhaCalc.push(simbFila.converteInteriro());
            }
            else if (simbFila.isOperador()){//testa se o elemento é operador
                int operandoA = pilhaCalc.pop();
                int operandoB = pilhaCalc.pop();
                int resultado;

                if (simbFila.toString().equals("+")){
                    resultado = operandoB + operandoA;
                }
                else if (simbFila.toString().equals("-")){
                    resultado = operandoB - operandoA;
                }
                else if (simbFila.toString().equals("*")){
                    resultado = operandoB * operandoA;
                }
                else {
                    resultado = operandoB / operandoA;
                }
                pilhaCalc.push(resultado);
            }
        }
        System.out.println(pilhaCalc.peek());// no topo da pilhaCalc está o resultado da expressão
    }
}
