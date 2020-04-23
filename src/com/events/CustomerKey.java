package com.events;

import java.util.Arrays;

public class CustomerKey {
	    private final String tenant ;
	    private final String event;
	    
	    CustomerKey(String t, String evt) {
	        this.event = evt;
	        this.tenant = t;
	    }
	    public String getEvent() { return event; }
	    public String getTenant() { return tenant; }
	    
	    @Override
	    public boolean equals(Object obj) {

            if (this == obj)
                return true;
            
            if (obj == null)
                return false;
            
            if (getClass() != obj.getClass())
                return false;
            
            CustomerKey other = (CustomerKey) obj;
            if (tenant != other.tenant)
                return false;
            if (event != other.event)
                return false;
            
            return true;
	    }
	    
	    @Override
	    public int hashCode() {
	    	byte[] thash = tenant.concat(event).getBytes();
	    	
	    	return Arrays.hashCode(thash);
	    }
	    
}
