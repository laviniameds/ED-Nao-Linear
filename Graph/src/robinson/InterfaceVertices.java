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
public interface InterfaceVertices {
    
    //private int chave;
    //private double valor;
    //public Vertices(int chave, double valor) 
    public abstract int getChave();

    /**
     * @param chave the chave to set
     */
    public abstract void setChave(int chave);

    /**
     * @return the valor
     */
    public abstract double getValor();

    /**
     * @param valor the valor to set
     */
    public abstract void setValor(double valor);
}