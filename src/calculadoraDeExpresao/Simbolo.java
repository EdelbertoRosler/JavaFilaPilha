package calculadoraDeExpresao;
/**
 *
 * @author edelb
 */
class Simbolo{
    private String simb;

    public Simbolo(String simb){
        this.simb = simb;
    }

    public boolean isOperando(){
        return Character.isDigit(this.simb.charAt(0));
    }

    public boolean isOperador(){
        return this.simb.equals('+') ||
                this.simb.equals('-') ||
                this.simb.equals('*') ||
                this.simb.equals('/');
    }

    public boolean isAbreParenteses(){
        return this.simb.equals('(');
    }

    public boolean isFechaParenteses(){
        return this.simb.equals(')');
    }

    public int verificarPrioridaade(){
        if (this.simb.equals('+') || this.simb.equals('-')){
            return 1;
        }
        if (this.simb.equals('*') || this.simb.equals('/')){
            return 2;
        }
        else if (this.isAbreParenteses()){
            return 0;
        }
        else {
            return -1;
        }
    }

    public String toString(){
        return this.simb;
    }
}
