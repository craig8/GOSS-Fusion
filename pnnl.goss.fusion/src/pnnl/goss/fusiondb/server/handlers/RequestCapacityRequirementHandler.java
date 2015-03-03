/*
	Copyright (c) 2014, Battelle Memorial Institute
    All rights reserved.
    Redistribution and use in source and binary forms, with or without
    modification, are permitted provided that the following conditions are met:
    1. Redistributions of source code must retain the above copyright notice, this
    list of conditions and the following disclaimer.
    2. Redistributions in binary form must reproduce the above copyright notice,
    this list of conditions and the following disclaimer in the documentation
    and/or other materials provided with the distribution.
    THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
    ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
    WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
     
    DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
    ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
    (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
    LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
    ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
    (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
    SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
    The views and conclusions contained in the software and documentation are those
    of the authors and should not be interpreted as representing official policies,
    either expressed or implied, of the FreeBSD Project.
    This material was prepared as an account of work sponsored by an
    agency of the United States Government. Neither the United States
    Government nor the United States Department of Energy, nor Battelle,
    nor any of their employees, nor any jurisdiction or organization
    that has cooperated in the development of these materials, makes
    any warranty, express or implied, or assumes any legal liability
    or responsibility for the accuracy, completeness, or usefulness or
    any information, apparatus, product, software, or process disclosed,
    or represents that its use would not infringe privately owned rights.
    Reference herein to any specific commercial product, process, or
    service by trade name, trademark, manufacturer, or otherwise does
    not necessarily constitute or imply its endorsement, recommendation,
    or favoring by the United States Government or any agency thereof,
    or Battelle Memorial Institute. The views and opinions of authors
    expressed herein do not necessarily state or reflect those of the
    United States Government or any agency thereof.
    PACIFIC NORTHWEST NATIONAL LABORATORY
    operated by BATTELLE for the UNITED STATES DEPARTMENT OF ENERGY
    under Contract DE-AC05-76RL01830
*/
package pnnl.goss.fusiondb.server.handlers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pnnl.goss.core.DataError;
import pnnl.goss.core.DataResponse;
import pnnl.goss.core.Request;
import pnnl.goss.core.server.AbstractRequestHandler;
import pnnl.goss.core.server.annotations.RequestHandler;
import pnnl.goss.core.server.annotations.RequestItem;
import pnnl.goss.fusiondb.datamodel.CapacityRequirementValues;
import pnnl.goss.fusiondb.requests.RequestCapacityRequirement;
import pnnl.goss.fusiondb.server.datasources.FusionDataSource;

@RequestHandler(value = { 
		@RequestItem(value=RequestCapacityRequirement.class) })
public class RequestCapacityRequirementHandler extends AbstractRequestHandler{
	
	public DataResponse handle(Request request) {
		
		DataResponse response = new DataResponse();
		Connection connection = FusionDataSource.getInstance().getConnection();
		
		try{
			
			RequestCapacityRequirement request1 = (RequestCapacityRequirement)request;
			Statement stmt = connection.createStatement();
			ResultSet rs = null;
			
			String query = "select * from capacity_requirements where `timestamp` = '"+request1.getTimestamp()+"'";
			if(request1.getIntervalId()!=0)
					query += " and interval_id = "+request1.getIntervalId();
			
			System.out.println(query);
			rs = stmt.executeQuery(query);
			
			List<String> timestampsList = new ArrayList<String>();
			List<Integer> confidenceList = new ArrayList<Integer>();
			List<Integer> intervalIdList = new ArrayList<Integer>();
			List<Integer> upList = new ArrayList<Integer>();
			List<Integer> downList = new ArrayList<Integer>();
			
			while (rs.next()) {
				timestampsList.add(rs.getString(1));
				confidenceList.add(rs.getInt(2));
				intervalIdList.add(rs.getInt(3));
				upList.add(rs.getInt(4));
				downList.add(rs.getInt(5));
				
			}

			CapacityRequirementValues data = new CapacityRequirementValues();
			data.setTimestamp(timestampsList.toArray(new String[timestampsList.size()]));
			data.setConfidence(confidenceList.toArray(new Integer[confidenceList.size()]));
			data.setIntervalId(intervalIdList.toArray(new Integer[intervalIdList.size()]));
			data.setUp(upList.toArray(new Integer[upList.size()]));
			data.setDown(downList.toArray(new Integer[downList.size()]));
			
			response.setData(data);
			connection.close();
		
		}
		catch(Exception e){
			e.printStackTrace();
			DataError error = new DataError(e.getMessage());
			response.setData(error);
			return response;
		}
		return response;
	}
	
	

}