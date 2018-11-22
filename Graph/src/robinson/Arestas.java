package robinson;

public class Arestas implements InterfaceArestas {
    private Vertices verticeOrigem;
    private Vertices verticeDestino;
    private double valor;
    private boolean direcionada;
   
    public Arestas(Vertices verticeOrigem, Vertices verticeDestino) {
        super();
        this.verticeOrigem=verticeOrigem;
        this.verticeDestino=verticeDestino;
        direcionada=false;
    }
    public Arestas(Vertices verticeOrigem, Vertices verticeDestino,
                   double valor) {
        super();
        this.verticeOrigem=verticeOrigem;
        this.verticeDestino=verticeDestino;
        this.valor = valor;
        direcionada=false;
    }
    /**
     * @param valor
     * @param direcionada
     */
    public Arestas(Vertices verticeOrigem, Vertices verticeDestino,
                   double valor, boolean direcionada) {
        super();
        this.verticeOrigem=verticeOrigem;
        this.verticeDestino=verticeDestino;
        this.valor = valor;
        this.direcionada = direcionada;
    }
    /**
     * @return Returns the verticeDestino.
     */
    public Vertices getVerticeDestino() {
        return verticeDestino;
    }
    /**
     * @param verticeDestino The verticeDestino to set.
     */
    public void setVerticeDestino(Vertices verticeDestino) {
        this.verticeDestino = verticeDestino;
    }
    /**
     * @return Returns the verticeOrigem.
     */
    public Vertices getVerticeOrigem() {
        return verticeOrigem;
    }
    /**
     * @param verticeOrigem The verticeOrigem to set.
     */
    public void setVerticeOrigem(Vertices verticeOrigem) {
        this.verticeOrigem = verticeOrigem;
    }


    /**
     * @return  direcionada
     */
    public boolean Direcionada() {
        return direcionada;
    }

    /**
     * @param direcionada the direcionada to set
     */
    public void setDirecionada(boolean direcionada) {
        this.direcionada = direcionada;
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

    /**
     * @return the verticeX
     */
    public String toString(){
        //return "["+verticeOrigem+"-"+verticeDestino+":"+valor+"]";        
        return "["+valor+"]";
    }
    
}
