/*
 * Mutability Detector
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *
 * Further licensing information for this project can be found in 
 * 		license/LICENSE.txt
 */

package org.mutabilitydetector.junit.matchers;

import static org.mutabilitydetector.junit.AnalysisSessionHolder.analysisResultFor;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.mutabilitydetector.IAnalysisSession.AnalysisResult;

public  class ClassAnalysisResultMatcher extends ConvertingTypeSafeMatcher<Class<?>, AnalysisResult> {

	private final TypeSafeDiagnosingMatcher<AnalysisResult> resultMatcher;

	public ClassAnalysisResultMatcher(TypeSafeDiagnosingMatcher<AnalysisResult> resultMatcher) {
		this.resultMatcher = resultMatcher;
	}
	
	@Override public AnalysisResult convertTo(Class<?> from) {
		return analysisResultFor(from);
	}

	@Override public boolean matchesConverted(AnalysisResult item, Description mismatchDescription) {
		if(resultMatcher.matches(item)) {
			return true;
		} else {
			resultMatcher.describeMismatch(item, mismatchDescription);
			return false;
		}
	}

	@Override public void describeTo(Description description) {
		description.appendText("a class with analysis result matching");
		resultMatcher.describeTo(description);
	}
}
