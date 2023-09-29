package org.smartregister.fhircore.engine.util.helper;
/*
 * #%L
 * Matchbox Engine
 * %%
 * Copyright (C) 2022 ahdis
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import java.util.List;

import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.r4.context.IWorkerContext;
import org.hl7.fhir.r4.elementmodel.Manager;
import org.hl7.fhir.r4.model.Base;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.StructureDefinition;
import org.hl7.fhir.r4.utils.StructureMapUtilities;

public class TransformSupportServicesMatchBox implements StructureMapUtilities.ITransformerServices {

    private List<Base> outputs;
    private IWorkerContext context;
    protected static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(TransformSupportServices.class);

    public TransformSupportServicesMatchBox(IWorkerContext worker, List<Base> outputs) {
        this.context = worker;
        this.outputs = outputs;
    }

    @Override
    public Base createType(Object appInfo, String name) throws FHIRException {
        StructureDefinition sd = context.fetchResource(StructureDefinition.class, name);
        return Manager.build(context, sd);
    }

    @Override
    public Base createResource(Object appInfo, Base res, boolean atRootofTransform) {
        if (atRootofTransform)
            outputs.add(res);
        return res;
    }

    @Override
    public Coding translate(Object appInfo, Coding source, String conceptMapUrl) throws FHIRException {
        ConceptMapEngine cme = new ConceptMapEngine(context);
        return cme.translate(source, conceptMapUrl);
    }

    @Override
    public Base resolveReference(Object appContext, String url) throws FHIRException {
        org.hl7.fhir.r4.model.Resource resource = context.fetchResource(org.hl7.fhir.r4.model.Resource.class, url);
        return resource;
//    if (resource != null) {
//      String inStr = FhirContext.forR4Cached().newJsonParser().encodeResourceToString(resource);
//      try {
//        return Manager.parseSingle(context, new ByteArrayInputStream(inStr.getBytes()), FhirFormat.JSON);
//      } catch (IOException e) {
//        throw new FHIRException("Cannot convert resource to element model");
//      }
//    }
//    throw new FHIRException("resolveReference, url not found: " + url);
    }

    @Override
    public List<Base> performSearch(Object appContext, String url) throws FHIRException {
        throw new FHIRException("performSearch is not supported yet");
    }

    @Override
    public void log(String message) {
        log.debug(message);
    }
}

