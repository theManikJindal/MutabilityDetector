/* 
 * Mutability Detector
 *
 * Copyright 2009 Graham Allan
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * 		http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.mutabilitydetector;

import static org.mutabilitydetector.ImmutableAssert.assertImmutable;

import org.junit.Before;
import org.junit.Test;
import org.mutabilitydetector.benchmarks.ImmutableExample;



public class AnalysisSessionTest {

	private Class<ImmutableExample> immutableClass;
	
	@Before public void setUp() {
		immutableClass = ImmutableExample.class;
	}

	@Test public void analysisOfImmutableExampleWillBeRegistered() throws Exception {
		IAnalysisSession analysisSession = AnalysisSession.createWithCurrentClassPath();
		IMutabilityCheckerFactory checkerFactory = new MutabilityCheckerFactory();
		ICheckerRunnerFactory checkerRunnerFactory = new CheckerRunnerFactory(null);
		AllChecksRunner checker = new AllChecksRunner(checkerFactory, checkerRunnerFactory, immutableClass);

		checker.runCheckers(analysisSession);
		
		AnalysisResult result = analysisSession.resultFor(immutableClass.getCanonicalName());
		assertImmutable(result);
	}
	
	@Test public void analysisWillBeRunForClassesWhenQueriedOnImmutableStatus() throws Exception {
		IAnalysisSession analysisSession = AnalysisSession.createWithCurrentClassPath();
		AnalysisResult result = analysisSession.resultFor(immutableClass.getCanonicalName());
		assertImmutable(result);
	}
	
}