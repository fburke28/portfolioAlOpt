package com.po.domain.ski;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Snow RSS domain object.
 * 
 * @author fburke
 */
@XmlRootElement(name="rss")
public class SnowRSS {

	private ChannelRSS channel;

	public ChannelRSS getChannel() {
		return channel;
	}

	public void setChannel(ChannelRSS channel) {
		this.channel = channel;
	}

}