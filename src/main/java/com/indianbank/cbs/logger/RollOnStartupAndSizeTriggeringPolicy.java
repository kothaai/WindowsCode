package com.indianbank.cbs.logger;

import ch.qos.logback.core.joran.spi.NoAutoStart;
import ch.qos.logback.core.rolling.DefaultTimeBasedFileNamingAndTriggeringPolicy;
import ch.qos.logback.core.rolling.RolloverFailure;

@NoAutoStart
	public class RollOnStartupAndSizeTriggeringPolicy<E> extends DefaultTimeBasedFileNamingAndTriggeringPolicy<E> {
	    @Override
	    public void start() {
	        super.start();
	        nextCheck = 0L;
	        isTriggeringEvent(null, null);
	        try {
	            tbrp.rollover();
	        } catch (RolloverFailure e) {
	            //Do nothing
	        }
	    }
	}

