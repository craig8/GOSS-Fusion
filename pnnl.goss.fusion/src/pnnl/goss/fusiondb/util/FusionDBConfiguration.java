///*
//	Copyright (c) 2014, Battelle Memorial Institute
//    All rights reserved.
//    Redistribution and use in source and binary forms, with or without
//    modification, are permitted provided that the following conditions are met:
//    1. Redistributions of source code must retain the above copyright notice, this
//    list of conditions and the following disclaimer.
//    2. Redistributions in binary form must reproduce the above copyright notice,
//    this list of conditions and the following disclaimer in the documentation
//    and/or other materials provided with the distribution.
//    THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
//    ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
//    WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
//     
//    DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
//    ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
//    (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
//    LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
//    ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
//    (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
//    SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
//    The views and conclusions contained in the software and documentation are those
//    of the authors and should not be interpreted as representing official policies,
//    either expressed or implied, of the FreeBSD Project.
//    This material was prepared as an account of work sponsored by an
//    agency of the United States Government. Neither the United States
//    Government nor the United States Department of Energy, nor Battelle,
//    nor any of their employees, nor any jurisdiction or organization
//    that has cooperated in the development of these materials, makes
//    any warranty, express or implied, or assumes any legal liability
//    or responsibility for the accuracy, completeness, or usefulness or
//    any information, apparatus, product, software, or process disclosed,
//    or represents that its use would not infringe privately owned rights.
//    Reference herein to any specific commercial product, process, or
//    service by trade name, trademark, manufacturer, or otherwise does
//    not necessarily constitute or imply its endorsement, recommendation,
//    or favoring by the United States Government or any agency thereof,
//    or Battelle Memorial Institute. The views and opinions of authors
//    expressed herein do not necessarily state or reflect those of the
//    United States Government or any agency thereof.
//    PACIFIC NORTHWEST NATIONAL LABORATORY
//    operated by BATTELLE for the UNITED STATES DEPARTMENT OF ENERGY
//    under Contract DE-AC05-76RL01830
//*/
//package pnnl.goss.fusiondb.util;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.Dictionary;
//import java.util.Properties;
//
//import pnnl.goss.fusiondb.server.datasources.FusionDataSource;
//
//@SuppressWarnings("rawtypes")
//public class FusionDBConfiguration {
//	/**
//	 * <p>
//	 * The configuration file in $SMX_HOME/etc will be CONFIG_PID.cfg
//	 * </p>
//	 */
//	public static final String CONFIG_PID = "pnnl.goss.fusiondb.server";
//	private static Dictionary configProperties;
//	
//	private static final String CONFIG_FILENAME = "/goss-fusiondb-server.properties";
//	public static final String CONFIG_DB_URI = "databaseURI";
//	public static final String CONFIG_DB_USER = "databaseUser";
//	public static final String CONFIG_DB_PW = "databasePassword";
//	
//
//	public static String getProperty(String propertyName){
//		if(configProperties!=null){
//			return (String)configProperties.get(propertyName);
//		} else {
//			System.err.println("FusionDB server Configuration not found, loading from file");
//			Properties props = loadFromFile();
//			return props.getProperty(propertyName);
//		}
//		
//	}
//
//	public static void setConfigProperties(Dictionary props){
//		configProperties = props;
//	}
//	private static Properties loadFromFile(){
//		Properties configProperties = new Properties();
//		
//		// Grabs the config file from the resources path which is on the class path.
//		InputStream input = FusionDataSource.class.getResourceAsStream(CONFIG_FILENAME);
//		try {
//			configProperties.load(input);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			System.err.println("Error occurred retreiving gridmw server config from file"+e);
//			e.printStackTrace();
//		}
//		
//		return configProperties;
//	}
//}
