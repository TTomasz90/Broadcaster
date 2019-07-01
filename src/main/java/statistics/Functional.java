
/** 
 *  Functional Interface
 *  Author: Tomasz Turek
 *	Description: Implementation signature for user defined Lambda functions
 *
 */

package statistics;

public interface Functional<T> {

	T operation(T a, T b);

}
