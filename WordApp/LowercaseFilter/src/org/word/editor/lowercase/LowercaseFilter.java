/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.word.editor.lowercase;

/**
 *
 * @author Aljarhi
 */
import org.openide.util.lookup.ServiceProvider;
import org.word.editor.api.TextFilter;

@ServiceProvider(service=TextFilter.class)

public class LowercaseFilter implements TextFilter {

    public String process(String s) {
        return s.toLowerCase();
    }
}
