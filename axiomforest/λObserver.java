/** Ben F Rayfield offers this software opensource MIT license */
package axiomforest;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

import axiomforest_old.observe.Λ;
import wikibinator101.wikibinatornodes.Callquad;
import wikibinator101.wikibinatornodes.HaltedLambda;

/** TODO rewrite comments cuz merging 2 classes that are both kinds of 3-way forest.
<br><br>
aka SetOfAxiomsUnobserved.
impliesNo and impliesYes is a subset of what ΛObserver can do,
but ΛObserver can imply things from false statements depending on the axioms.
λObserver started as planning a 3-way-forest where all nodes you keep are true
and only imply more true nodes, then wanted to also imply that the statement
that for 4 lambdas b c d e: (b c)->d and (b c)->e cant both be true at once,
since every call of lambda on lambda returns at most 1 lambda (I have a 1-to-1 mapping between lambdas and integers,
see the numOfUniqBinaryForestShapesAtHeight(x) = numOfUniqBinaryForestShapesAtHeight(x-1)^2+1
and numOfUniqBinaryForestShapesAtHeight(0)=1, but excluding those which have more than 5 curries
(lambda.l.l.l.l.l is leaf if its 5 curries, lambda is leaf if its 0 curries), so skip those in counting them.
Then I started upgrading to Λ (3-way and a bit) but found
I still need the λ objects (3-way) to match forest shapes efficiently.
<br><br>
** aka SetOfAxiomsObserved *
public interface ΛObserver{
	
	public Λ leaf();
	
	** an optimization of an axiom is also an axiom. These UnaryOperator<Observedλ>s are axioms. *
	public Set<UnaryOperator<Λ>> implies();
	
	** of trinaryForestNodes: forall x forall y: <trinaryForestLeaf x y> is YES, regardless of x and y being YES andOr NO,
	which allows a halted lambda to see any nonhalted part of the system such as to generate a custom kind of id of it
	and to compute statements about all possible statements that can be said in the system but only about 1 at a time.
	This returns <YES trinaryForestLeaf x y> for its 2 params x y.
	*
	public BinaryOperator<Λ> joiner();
}
<br><br>
*/
public interface λObserver{
	
	TODO there needs to be 1 main UnaryOperator and all other UnaryOperators are optimizations of it,
	but that main UnaryOperator can be a map of someλ.v().l() to UnaryOperator
	since anyλ.v().l() is used as name of an axiom, and anyλ.v().r() is its "formalVerified curried params",
	and anyλ.l() and anyλ.r() are its params that you can put anything regardless of its TruthValues.
	That UnaryOperator named STEP (which wraps any subset of the UnaryOperator across the p2p network, your local axioms)
	will have 2 main ways of using it: directly, to step just 1 ahead, and in an optimized way
	to go many steps ahead such as by wrapping lwjgl opencl (gpu) matmul or neuralnet calculations etc...
	wrapping that in a UnaryOperator that looks for certain patterns in the forest that it knows
	how to compute faster (than interpreted mode). But... maybe that should be done only by it creating Rfpd
	and using interpreted mode to DebugStepOver that Rfpd.
	If thats the design I want, then there needs to be a way to request the creation of the Rfpd.
	A Rfpd is a shape of tinary forest, such as Rfpd.java in wikibinator will be a wrapper for a λ
	and will in cases of GPU optimizations etc lazyEval the λ and instead store it in float[] or byte[] etc,
	only viewing that as cbt (another shape of λ) when you call the λ functions to view those parts of the forest.
	If you instead use λ.g(long) strategicly, and similar funcs, you can jump from the wrapper of the float[]
	to a specific float, and get that float (or int, byte, long, double, etc) without creating a λ wrapping the float
	and without creating the λ internal nodes of the cbt such as pair of 2 floats, and pair of pairs of 4 8 16 floats,
	and the 16 bits of the high or low bits of the float, and down to T or F
	(todo which kind, theres axiomforest.λ kind of T and F and theres wikibinator T and F which are a higher level).
	I want the λ created by optimizations and for STEP to ONLY use interpreted mode,
	so I need a way to request the creation of a λ, kind of like requesting a loop of step some nondeterministic
	number of times step.step.step... but actually compute it by lwjgl opencl or javassist or acyclicflow or python etc.
	If I ask for a nondeterministic number of steps, it breaks the selfreference of being deterministic,
	so I want to just "happen to find" the λ as rfpd that can be used to debugStepOver what the GPU did.
	but I need some way to define what I'm looking for. I cant just ask for an InfiniteSetContains.java instance
	whose (HaltedLambda)infiniteSet() is a certain HaltedLambda and use that cuz there can be multiple things
	in the set and its nondeterministic which one you'd find even though its deterministic how to
	generate a InfiniteSetContains which is a λ. You'd be asking for any of those which have the same
	InfiniteSetContains.infiniteSet() and you're looking for the InfiniteSetContains.memberOfSet()
	which would be the answer to your question. Or simpler, you just ask for the return value of a certain Callquad.
	Ignore that infiniteset stuff for now. Asking for return value of Callquad defines whats wanted.
	But since theres different "gas" costs nondeterministicly depending on what kind of computer it is,
	such as some have a strong GPU and some will be wrappers of GPU clouds and some will have many CPUs but weak GPU,
	and other variations of compute resources available... to ask it to limit gas is nondeterministic
	of how much gas it costs, but close enough you can make it return within a chosen approx time limit and max memory.
	A Map<Callquad,Double> could tell priority, or something about an energy function
	(energy of whole system is this much higher when you dont have rfpd of what this callquad returns, or something like that)
	so the system would try to minimize total energy by having those rfpd (callquad->...->returnVal, is a rfpd) cached,
	so if you ask for "any solution to the InfiniteSetContains for what does that callquad return",
	or some similar datastruct specialized in just getting returns (not complicating it by defining it as a set),
	then deterministicly theres only 1 possible value for it (unless bull exists in the system or the axioms arent specific
	enough to make each possible bit be a specific value that can eventually be proven, which they're probably
	not for all the bits but are for certain subsets of bits and we can try to stay within those).
	TODO how do I hook those optimizations in?
	
	TODO even though its technically a 3+2bitsofTruthvalue way forest, store it as a 4-way forest with the 4th child being
	cache of superposition() aka the form of it where its all TruthValue.unknown cuz thats needed to match trinary forest shape,
	and if its x.superposition()==x then just store all 0s there meaning null.
	Leave 32 or 64 bits in the 256 bit ids (yes, down from 512, always 256 cuz store as 4 childs) for some kind of header,
	similar to Wikibinator HeaderBits but with selfreference fixed so all header bits have to be deriveable from what
	a HaltedLambda can see in its param thats a wrapper of a λ, so any HaltedLambda can be used as an idMaker
	to compute new kinds of ids at runtime (for redundant security and optimizations).
	Ids are 256 bits and maybe use sha3_224.
	Ids will include up to 128 bits of literal data of cbt.
	Eventually more kinds of ids will be derived that have optimizations for lazyeval of the 3 way forest so that
	nearly all 3 way forest nodes are never created, instead using λ.g(long) etc to skip many steps of a calculation
	of certain common combos of calculations and anything else has to observe all the λ between.
	Its very important for the Wikibinator Curry op, which counts down in unary (T (T (T wikibinatorLeaf))) being unary3,
	and prefixes the next param to a linkedlist (where funcBody is second last thing in that linkedList)...
	Its very important for that to be optimizable, as Compiled.java instances (which now have to deal with these UnaryOperator<λ>,
	or some merging of those, while keeping axiomforest more general than Wikibinator or Occamsfuncer etc)...
	Its very important for that optimization to fit with the kinds of ids that can be efficiently derived of the 3 way forest nodes
	(which will actually be stored as 4 way including 1 of them being cache of superposition() node).
	
	
	
	
	
	/** is a yes. Start from here, and impliesYes and joiner to get everything possible. */
	public λ leaf();
	
	/** Example: If you want to know what a certain 3 way forest shape, with just its top node being yes, implies is also yes,
	then param of the UnaryOperator<λ> has λ.allUnknownBelow()==true and λ.tv()==TruthValue.yes.
	Example: If you want to do strong turingCompleteChallengeResponse then use a param of λ.allObserve()==true
	which normally will return another with λ.allObserve()==true but a UnaryOperator
	could return anything as long as the same UnaryOperator never gives 2 different returns for same param
	or if you've somehow set it up (specificly about spend/wallet calls etc) that you're sure it will
	only be called at most once on each param including anyone else in the world will never observe
	that same part of the shared axiomforest bloomFilter which will often be done
	to mount temp calculations into the bloomFilter at some random place such as a random 256 bit number,
	similar to memory mapping but the bloomFilter can be written at most once in each place
	or if twice then things have to be adjusted to bring it back to consistency considering all axioms.
	*/
	public Set<UnaryOperator<λ>> implies();
	
	/** If param is true then it implies the return is true *
	public Set<UnaryOperator<λ>> impliesYes();
	
	/** If param is true then it implies the return is false *
	public Set<UnaryOperator<λ>> impliesNo();
	*/
	
	/** of trinaryForestNodes: forall x forall y: <trinaryForestLeaf x y> is YES, regardless of x and y being YES andOr NO,
	which allows a halted lambda to see any nonhalted part of the system such as to generate a custom kind of id of it
	and to compute statements about all possible statements that can be said in the system but only about 1 at a time.
	This returns <trinaryForestLeaf x y> for its 2 params x y.
	*/
	public BinaryOperator<λ> joiner();
	
	/* Λ.tv() is opposite my tv() but without having to know either,
	similar to from any corner of a hypercube the corners can be divided into 2 groups those of even vs odd parity,
	but things dont have to be organized like a hypercube its just here in case you want to use those kinds of axioms,
	or view it as all paths from here (UnaryOperator param) to/from there (UnaryOperator return) are odd parity,
	and if its unproveable then there there are no paths of even or odd length
	Vaguely might somehow be related to parity in physics andOr linear algebra but I dont want to speculate yet.
	I imagine using just impliesYes and impliesNo but this seems to complete the pattern.
	<br><br>
	See https://github.com/benrayfield/hypercubewave smooth curves about parity, which is the display of
	about 2^100 voxels of a 100 dimensional hypercube whose corners are each white or black depending
	on the parity of all paths length from any chosen corner and is trivially just log number of copy move invert average. 
	*
	public Set<UnaryOperator<λ>> odd();
	
	/* Λ.tv() is equal to my tv() but without having to know either,
	similar to from any corner of a hypercube the corners can be divided into 2 groups those of even vs odd parity,
	but things dont have to be organized like a hypercube its just here in case you want to use those kinds of axioms,
	or view it as all paths from here (UnaryOperator param) to/from there (UnaryOperator return) are even parity.
	and if its unproveable then there there are no paths of even or odd length.
	Vaguely might somehow be related to parity in physics andOr linear algebra but I dont want to speculate yet.
	I imagine using just impliesYes and impliesNo but this seems to complete the pattern.
	<br><br>
	See https://github.com/benrayfield/hypercubewave smooth curves about parity, which is the display of
	about 2^100 voxels of a 100 dimensional hypercube whose corners are each white or black depending
	on the parity of all paths length from any chosen corner and is trivially just log number of copy move invert average.
	*
	public Set<UnaryOperator<λ>> even();
	*/
	
}
