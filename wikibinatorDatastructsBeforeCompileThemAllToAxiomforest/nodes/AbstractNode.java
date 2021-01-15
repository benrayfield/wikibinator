/** Ben F Rayfield offers this software opensource MIT license */
package wikibinatorDatastructsBeforeCompileThemAllToAxiomforest.nodes;
import java.util.AbstractList;
import java.util.Iterator;

import axiomforest.observe.Λ;
import axiomforest.superposition.λ;
import wikibinatorDatastructsBeforeCompileThemAllToAxiomforest.Node;

public abstract class AbstractNode extends AbstractList<Node> implements Node{
	
	protected final Node[] childs;
	
	public AbstractNode(Node... childs){
		this.childs = childs;
	}
	
	public Node get(int index){
		return childs[index];
	}
	
	public int size(){
		return childs.length;
	}
	
	/** cant remove() */
	public Iterator<Node> iterator(){
		return new Iterator<Node>(){
			protected int index;
			public Node next(){ return childs[index++]; }
			public boolean hasNext(){ return index < childs.length; }
			public void remove(){ throw new UnsupportedOperationException(); }
		};
	}
	
	public boolean isLeaf(){
		return false;
	}
	
	//axiomforest.observe.Λ functions...
	
	public boolean a(){
		return isLeaf(); //FIXME is Node leaf different from the Λ leaf which might be a lower level than Nodes?
	}
	
	public boolean tv(){
		throw new RuntimeException("FIXME should Node extend axiomforest.observe.Λ vs axiomforest.superposition.λ ?"
			+" Id like to make Λ extend λ but I dont want them to Object.equals (and hashCode), so TODO think about that.");
	}
	
	public Λ v(){
		throw new RuntimeException("TODO I havent decided how to map between Node and Λ yet. Need wikibinator implemented as Nodes to pass testcases first.");
	}
	
	public Λ l(){
		throw new RuntimeException("TODO I havent decided how to map between Node and Λ yet. Need wikibinator implemented as Nodes to pass testcases first.");
	}
	
	public Λ r(){
		throw new RuntimeException("TODO I havent decided how to map between Node and Λ yet. Need wikibinator implemented as Nodes to pass testcases first.");
	}
	
	public λ superposition(){
		throw new RuntimeException("TODO");
	}
	
	public boolean equals(Object o){
		throw new RuntimeException("TODO compare List contents and class type. It will match axiomforest equality since each class type will not overlap any of the possible shapes of the others in axiomforest.");
	}
	
	public int hashCode(){
		throw new RuntimeException("TODO be very careful about this as it must match Λ.hashCode(). See the throw/comment of equals(Object).");
	}
	
	public Λ leaf(){
		return Leaf.instance; //FIXME is Λ leaf the same forest shape as Node leaf in axiomforest? Probably, but todo think more about that.
	}
	
	public Λ node(boolean tv, Λ v, Λ l, Λ r){
		throw new RuntimeException("TODO");
	}
	
	public Node step(){
		throw new RuntimeException("TODO");
	}

}