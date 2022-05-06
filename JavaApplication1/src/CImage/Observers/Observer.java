/*
 * Observer.java
 *
 * Created on 8 juillet 2007, 11:03
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package CImage.Observers;

import CImage.*;

/**
 *
 * @author HP_Propriétaire
 */
public interface Observer 
{
    public void   setCImage(CImage ci);
    public CImage getCImage();
    public void   update();
}
