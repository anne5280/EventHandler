 package com.events;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Topic: Java Functional Programming versus Java Method Reflection
 * 
 * This proof of concept application is a stand-alone Java application that uses Java Functional Program to process events.
 * When an event occurs, the application determines the appropriate method to execute to process the event based on a unique key.
 * The method to execute is a Predicate and returns true/false.
 * 
 * @author Anne George
 *
 */
public class EventMethods {

	// These Settlement checks can be reused in possibly 3 DRLS
    public static boolean validateMethodOne(EventHandler eventHandler) {
    	
        System.out.println("...executing validateMethodOne method.");
        
	    return true ; 
	}
	
    public static boolean validateMethodTwo(EventHandler eventHandler) {
    	
        System.out.println("...executing validateMethodTwo method.");
        

	    return true ; 
	}
	
	public static boolean getIncreasingIncrementalOffers (EventHandler eventHandler) {
	    Double currentChangeAmount = 0.0;
	    Double previusChangeAmount = 0.0;
	   
        System.out.println("...executing getIncreasingIncrementalOffers method.");	    
        	    
	    return (currentChangeAmount > previusChangeAmount) ? true : false;
	}	
	
    public static boolean getValueIsPresent(EventHandler s) {
    	
        System.out.println("...executing getValueIsPresent method.");

	    return false; 
	}

    public static boolean getAnotherValueIsPresent(EventHandler eventHandler) {
    	
    	System.out.println("...executing getAnotherValueIsPresent method.");
    	
    	return false; 
	}
	
	/**
	 * Example where two rules need to validate ThirdParty first, then their own rule is executed 
	 * 
	 * @param eh  EventHandler
	 * @param aMethod Function definition
	 * @return boolean True if methods produced a result.  Otherwise, false.
	 */
	public boolean validate(EventHandler eh, Predicate<EventHandler> aMethod) {
		
        Predicate<EventHandler> thirdParty = tp -> { 
        	
        	   System.out.println("...executing Third Party check.");
        	   
               return eh.getThirdPartyItem() != null && 
 			          eh.getThirdPartyItem().getTheLocation() != null ;
           };
        
 		boolean result = aMethod.test(eh);
 		
        return (thirdParty.test(eh)== true && result == true);
    }
	
	/**
	 * This method handles a mock load Key/Value pairs from properties or JSON or a database.
	 * The method is defined here and mapped to a key, but even the method definitions can be in JSON/XML/Properties.
	 * The methods are then executed.
	 * @param args String[]
	 */
	public static void main(String[] args) {

		EventMethods em = new EventMethods();

		// Initialize map of all events to functional method
		HashMap<CustomerKey, Predicate<EventHandler>> methodIndex = new HashMap<CustomerKey, Predicate<EventHandler>>();
		

		CustomerKey tk1 = new CustomerKey("customer1", "eventPossibleValue" );
		methodIndex.put(tk1, eh -> {return getValueIsPresent(eh) == false && getValueIsPresent(eh) == false;});	
		
		 tk1 = new CustomerKey("customer1", "eventVehicleCVBNAccruingStorage" );
		methodIndex.put(tk1, s -> {return getValueIsPresent(s) == false;});	
		
		CustomerKey tk2 = new CustomerKey("customer2", "eventPossibleValue" );
		methodIndex.put(tk2, s -> {System.out.println("...executing incrementalOffers EVENT"); 
				        return getIncreasingIncrementalOffers(s) == true;});				
		
		System.out.println("Start functional method execution.\n");
		
		em.validate(new EventHandler(), methodIndex.get(tk1)); 
		em.validate(new EventHandler(), methodIndex.get(tk2));
		
		System.out.println("\nMethod execution complete.");
		 
	}
}
