/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.util.List;

/**
 *
 * @author ignitedev
 * @param <T>
 */
public interface DBService<T> {
      void Add(T t);

    List<T> DisplayAll();

    void Delete(T t);

    void Update(T t);
    
}
