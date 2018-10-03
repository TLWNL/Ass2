package src;
/*
 * elements: objects of type E
 * structure: linear
 * domain: A row of elements of type E.
 *
 * constructor:
 * Set();
 * @precondition -
 * @postcondition - The new Set-object is empty
 */

public interface SetInterface<E extends Comparable> {
    void initSet();
    /*
     * @precondition -
     * @postcondition - The set has been emptied
     */

    boolean add(E l);
    /*
     * @precondition -
     * @postcondition - TRUE: Element l has been added to the set
     *                  FALSE: Element l has not been added to the set
     */

    boolean remove(;
    /*
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

    int size();
    /*
     * @precondition -
     * @postcondition - The size of the set has been returned
     */

    boolean contains(E l);
    /*
     * @precondition -
     * @postcondition - TRUE: The set contains the Element l
     *                - FALSE: The set does not contain the Element l
     */

    Set<E> difference(Set set2);
    /*
     * @precondition -
     * @postcondition - The difference between Set 1 and Set 2 has been calculated and returned
     */

    Set<E> intersection(Set set2);
    /*
     * @precondition -
     * @postcondition - The intersection between Set 1 and Set 2 has been calculated and returned
     */

    Set<E> union(Set set);
    /*
     * @precondition -
     * @postcondition - The union between Set 1 and Set 2 has been calculated and returned
     */

    Set<E> symmetricDifference(Set set2);
    /*
     * @precondition -
     * @postcondition - The symmetric difference between Set 1 and Set 2 has been calculated and returned
     */



    Set<E> copySet();
    /*
     * @precondition -
     * @postcondition - A deep copy of the set has been returned
     */

}
