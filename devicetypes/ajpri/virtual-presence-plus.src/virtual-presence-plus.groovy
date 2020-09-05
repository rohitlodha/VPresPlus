//Release History
//		1.0 May 20, 2016
//			Initial Release


metadata {
        definition (name: "Virtual Presence Plus", namespace: "ajpri", author: "Austin Pritchett") {
        capability "Switch"
        capability "Refresh"
        capability "Presence Sensor"
		capability "Sensor"
        
		command "arrived"
		command "departed"
    }

	// simulator metadata
	simulator {
	}

	 tiles {
        standardTile("presence", "device.presence", width: 2, height: 2, canChangeBackground: true) {
            state("not present", label:'not present', icon:"st.presence.tile.not-present", backgroundColor:"#ffffff", action:"arrived")
            state("present", label:'present', icon:"st.presence.tile.present", backgroundColor:"#00A0DC", action:"departed")
        }
        main "presence"
        details "presence"
    }
}

def parse(String description) {
	def pair = description.split(":")
	createEvent(name: pair[0].trim(), value: pair[1].trim())
}

// handle commands
def arrived() {
	on()
}


def departed() {
    off()
}

def on() {
	sendEvent(name: "switch", value: "on")
    sendEvent(name: "presence", value: "present")

}

def off() {
	sendEvent(name: "switch", value: "off")
    sendEvent(name: "presence", value: "not present")

}
