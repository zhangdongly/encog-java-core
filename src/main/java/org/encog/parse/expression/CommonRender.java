/*
 * Encog(tm) Core v3.3 - Java Version
 * http://www.heatonresearch.com/encog/
 * https://github.com/encog/encog-java-core
 
 * Copyright 2008-2014 Heaton Research, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *   
 * For more information on Heaton Research copyrights, licenses 
 * and trademarks visit:
 * http://www.heatonresearch.com/copyright
 */
package org.encog.parse.expression;

import org.encog.ml.prg.ProgramNode;
import org.encog.ml.prg.extension.ConstantPool;

/**
 * Common functions for some renders.
 */
public class CommonRender {
	public ExpressionNodeType determineNodeType(ProgramNode node) {

		if( node.getTemplate() instanceof ConstantPool) {
			return ExpressionNodeType.ConstVal;
		}

		if (node.getName().equals("#const")) {
			return ExpressionNodeType.ConstVal;
		}  
			
		if (node.getName().equals("#var")) {
			return ExpressionNodeType.Variable;
		} 
		
		if( node.getChildNodes().size()!=2 ) {
			return ExpressionNodeType.Function;
		}
		
		String name = node.getName();
		
		if( !Character.isLetterOrDigit(name.charAt(0)) ) {
			return ExpressionNodeType.Operator;			
		}
		
		return ExpressionNodeType.Function;		
	}
}
