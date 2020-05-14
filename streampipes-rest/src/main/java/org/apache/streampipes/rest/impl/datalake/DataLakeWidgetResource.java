/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.apache.streampipes.rest.impl.datalake;

import org.apache.streampipes.model.dashboard.DashboardWidgetModel;
import org.apache.streampipes.model.datalake.DataExplorerWidgetModel;
import org.apache.streampipes.rest.api.dashboard.IDashboardWidget;
import org.apache.streampipes.rest.api.dataexplorer.IDataExplorerWidget;
import org.apache.streampipes.rest.impl.AbstractRestInterface;
import org.apache.streampipes.rest.shared.annotation.JsonLdSerialized;
import org.apache.streampipes.rest.shared.util.SpMediaType;
import org.apache.streampipes.storage.api.IDashboardWidgetStorage;
import org.apache.streampipes.storage.api.IDataExplorerWidgetStorage;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/v3/users/{username}/datalake/dashboard/widgets")
public class DataLakeWidgetResource extends AbstractRestInterface implements IDataExplorerWidget {

  @GET
  @JsonLdSerialized
  @Produces(MediaType.APPLICATION_JSON)
  @Override
  public Response getAllDataExplorerWidgets() {
    return ok(asContainer(getDataExplorerWidgetStorage().getAllDataExplorerWidgets()));
  }

  @GET
  @JsonLdSerialized
  @Produces(SpMediaType.JSONLD)
  @Path("/{widgetId}")
  @Override
  public Response getDataExplorerWidget(@PathParam("widgetId") String widgetId) {
    return ok(getDataExplorerWidgetStorage().getDataExplorerWidget(widgetId));
  }

  @PUT
  @JsonLdSerialized
  @Consumes(SpMediaType.JSONLD)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/{widgetId}")
  @Override
  public Response modifyDataExplorerWidget(DataExplorerWidgetModel dataExplorerWidgetModel) {
    getDataExplorerWidgetStorage().updateDataExplorerWidget(dataExplorerWidgetModel);
    return ok();
  }

  @DELETE
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/{widgetId}")
  @Override
  public Response deleteDataExplorerWidget(@PathParam("widgetId") String widgetId) {
    getDataExplorerWidgetStorage().deleteDataExplorerWidget(widgetId);
    return ok();
  }

  @POST
  @JsonLdSerialized
  @Produces(SpMediaType.JSONLD)
  @Consumes(SpMediaType.JSONLD)
  @Override
  public Response createDataExplorerWidget(DataExplorerWidgetModel dataExplorerWidgetModel) {
    String widgetId = getDataExplorerWidgetStorage().storeDataExplorerWidget(dataExplorerWidgetModel);
    return ok(getDataExplorerWidgetStorage().getDataExplorerWidget(widgetId));
  }

  private IDataExplorerWidgetStorage getDataExplorerWidgetStorage() {
    return getNoSqlStorage().getDataExplorerWidgetStorage();
  }

}