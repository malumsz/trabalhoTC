
package content.common;

public interface Transition<S extends AbstractState> {
    
    public S getNext();
    
    public void goTo(S t);
    
    public boolean isValid();
}
