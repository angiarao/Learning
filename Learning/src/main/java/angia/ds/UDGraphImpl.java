package angia.ds;
import org.jgrapht.EdgeFactory;
import org.jgrapht.graph.AbstractBaseGraph;


public class UDGraphImpl<V,E> extends AbstractBaseGraph<V, E>{
	private static final long serialVersionUID = -2156068167591708994L;
	protected UDGraphImpl(EdgeFactory<V, E> ef, boolean allowMultipleEdges, boolean allowLoops) {
		super(ef, allowMultipleEdges, allowLoops);
	}
}
