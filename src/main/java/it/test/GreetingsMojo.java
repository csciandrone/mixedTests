package it.test;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
@Mojo(name="helloPlugin")
public class GreetingsMojo extends AbstractMojo {

	public void execute() throws MojoExecutionException, MojoFailureException {
		try {
			getLog().info("Saluti "+InetAddress.getLocalHost()+"/"+InetAddress.getLocalHost().getHostName());
		} catch (UnknownHostException e) {
			getLog().info("Saluti, ip  non determinabile");
		}
		
	}

	

}
