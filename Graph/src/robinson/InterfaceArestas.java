/*
 * Created on 17/04/2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author Administrador
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface InterfaceArestas {
    //private Vertices verticeOrigem;
    //private Vertices verticeDestino;
    //private double valor;
    //private boolean direcionada;   
    //public Arestas(Vertices verticeOrigem, Vertices verticeDestino) 
    //public Arestas(Vertices verticeOrigem, Vertices verticeDestino,double valor)
    //public Arestas(Vertices verticeOrigem, Vertices verticeDestino,double valor, boolean direcionada)        
    
    /**
     * @return Returns the verticeDestino.
     */
    public abstract Vertices getVerticeDestino();

    /**
     * @param verticeDestino The verticeDestino to set.
     */
    public abstract void setVerticeDestino(Vertices verticeDestino);

    /**
     * @return Returns the verticeOrigem.
     */
    public abstract Vertices getVerticeOrigem();

    /**
     * @param verticeOrigem The verticeOrigem to set.
     */
    public abstract void setVerticeOrigem(Vertices verticeOrigem);

    /**
     * @return  direcionada
     */
    public abstract boolean Direcionada();

    /**
     * @param direcionada the direcionada to set
     */
    public abstract void setDirecionada(boolean direcionada);

    /**
     * @return the valor
     */
    public abstract double getValor();

    /**
     * @param valor the valor to set
     */
    public abstract void setValor(double valor);
}