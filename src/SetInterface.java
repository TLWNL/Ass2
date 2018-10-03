package src;
/*
 * elements: objects of type E
 * structure: linear
 * domain: All rows of elements of type E.
 *
 * constructor:
 * Set();
 * @precondition -
 * @postcondition - The new Set-object is empty
 */

public interface SetInterface<E extends Comparable> {
    Set<E> initSet();
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

    void printSet();
    /*
     * @precondition -
     * @postcondition - The set has been printed out
     *
     */

    int size();
    /*
     * @precondition -
     * @postcondition - The size of the set has been returned
     */

    boolean remove(int i);
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

    String get(int i);
    /*
     * @precondition -
     * @postcondition - Returns element i of the set in the form of a String
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

    boolean contains(E l);
    /*
     * @precondition -
     * @postcondition - TRUE: The set contains the Element l
     *                - FALSE: The set does not contain the Element l
     */
}
