package es.uned.managed.bean;

import java.util.List;
import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import es.uned.model.Inmueble;

public class InmuebleDataModel extends ListDataModel<Inmueble> implements SelectableDataModel<Inmueble> {  

    public InmuebleDataModel() {
    }

    public InmuebleDataModel(List<Inmueble> data) {
        super(data);
    }
    
    @Override
    public Inmueble getRowData(String rowKey) {
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data
        
        List<Inmueble> inmuebles = (List<Inmueble>) getWrappedData();
        
        for(Inmueble inmueble : inmuebles) {
        	System.out.println("getRowData" + String.valueOf(inmueble.getId()));
        	
            if(String.valueOf(inmueble.getId()).trim().equals(rowKey)){
            	System.out.println(inmueble.getId());
                return inmueble;
            }    
        }
        
        return null;
    }

    @Override
    public Object getRowKey(Inmueble inmueble) {
        return String.valueOf(inmueble.getId()).trim();
        
    }
}
