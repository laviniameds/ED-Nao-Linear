
public class Vertices implements InterfaceVertices {
    private int chave;
    private double valor;
    /**
     * @param chave
     * @param valor
     */
    public Vertices(int chave, double valor) {
        super();
        this.chave = chave;
        this.valor = valor;
    }
    /**
     * @return the chave
     */
    public int getChave() {
        return chave;
    }
    /**
     * @param chave the chave to set
     */
    public void setChave(int chave) {
        this.chave = chave;
    }
    /**
     * @return the valor
     */
    public double getValor() {
        return valor;
    }
    /**
     * @param valor the valor to set
     */
    public void setValor(double valor) {
        this.valor = valor;
    }
    
    public String toString(){        
        //return "["+chave+" - "+valor+"]";
        return "["+chave+"]";
    }
    
}
