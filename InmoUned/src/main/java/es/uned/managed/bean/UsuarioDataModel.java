/*
 * Copyright 2009-2011 Prime Technology.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package es.uned.managed.bean;

import java.util.List;
import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;


import es.uned.model.Usuario;

public class UsuarioDataModel extends ListDataModel<Usuario> implements SelectableDataModel<Usuario> {  

    public UsuarioDataModel() {
    }

    public UsuarioDataModel(List<Usuario> data) {
        super(data);
    }
    
    @Override
    public Usuario getRowData(String rowKey) {
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data
        
        List<Usuario> usuarios = (List<Usuario>) getWrappedData();
        
        for(Usuario usuario : usuarios) {
        	System.out.println("getRowData" + usuario.getId());
            if(String.valueOf(usuario.getId()).equals(rowKey)){
            	System.out.println(usuario.getId());
                return usuario;
            }    
        }
        
        return null;
    }

    /*
    @Override  
    public Usuario getRowData(String rowKey) {  
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data  
          
        List<Usuario> usus = (List<Usuario>) getWrappedData();  
          
        for(Usuario usu: usus) {  
            if(usu.getEmail().equals(rowKey))  
                return usu;  
        }  
          
        return null;  
    }  */
    
    @Override
    public Object getRowKey(Usuario usuario) {
        return String.valueOf(usuario.getId());
    //	   return usuario.getEmail();
    }
}
