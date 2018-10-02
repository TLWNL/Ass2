package src;

/**@elements : objects of type E
 * @structure : linear
 * @domain : ??!!
 *
 * @constructor : Set(Class<E> set);
 * <dl>
 *     <dt><b>PRE-condition</b></dt>    -
 *     <dt><b>POST-condition</b></dt>   The new Set object of type E is empty
 * </dl>
 *
 * @param <E>
 **/

public interface SetInterface<E extends Comparable> {
    void initSet();
    /*
     * @precondition -
     * @postcondition - The set has been emptied
     */

    void add(E toAdd);
    /* CHANGE TO BOOLEAN
     * @precondition -
     * @postcondition - TRUE: E toAdd has been added to the set
     *                  FALSE: E toAdd has not been added to the set
     */

    void printSet();
    /*
     * @precondition -
     * @postcondition - The Set has been printed out
     *
     */

    int size();
    /*
     * @precondition -
     * @postcondition - The size of the set has been returned
     */

    boolean remove(int i);
    /* CHANGE TO BOOLEAN
     * @precondition -
     * @postcondition - TRUE: The element at index i of the set has been removed
     *                  FALSE: The element at index i of the set has not been removed
     */

    boolean isEmpty();
    /*
     * @precondition -
     * @postcondition - TRUE: The set is empty
     *                  FALSE: The set is not empty
     */

    //StringBuffer get(int i);
    /*
     * @precondition -
     * @postcondition - Returns element i of the set in the form of a StringBuffer
     */

    Set difference(Set set2);
    /*
     * @precondition -
     * @postcondition - The difference between Set 1 and Set 2 has been calculated and returned
     */

    Set intersection(Set set2);
    /*
     * @precondition -
     * @postcondition - The intersection between Set 1 and Set 2 has been calculated and returned
     */

    Set union(Set set);
    /*
     * @precondition -
     * @postcondition - The union between Set 1 and Set 2 has been calculated and returned
     */

    Set symmetricDifference(Set set2);
    /*
     * @precondition -
     * @postcondition - The symmetric difference between Set 1 and Set 2 has been calculated and returned
     */

    public boolean contains(E src);
    /* CHANGE TO CONTAINS
     * @precondition -
     * @postcondition - TRUE: The set contains the E src
     *                - FALSE: The set does not contain the E src
     */
}
