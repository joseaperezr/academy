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


import es.uned.model.Interesado;

public class InteresadoDataModel extends ListDataModel<Interesado> implements SelectableDataModel<Interesado> {  

    public InteresadoDataModel() {
    }

    public InteresadoDataModel(List<Interesado> data) {
        super(data);
    }
    
    @Override
    public Interesado getRowData(String rowKey) {
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data
        
        List<Interesado> interesados = (List<Interesado>) getWrappedData();
        
        for(Interesado interesado : interesados) {
        	System.out.println("getRowData" + String.valueOf(interesado.getId()));
        	
            if(String.valueOf(interesado.getId()).trim().equals(rowKey)){
            	System.out.println(interesado.getId());
                return interesado;
            }    
        }
        
        return null;
    }

    @Override
    public Object getRowKey(Interesado interesado) {
        return String.valueOf(interesado.getId()).trim();
        
    }
}
