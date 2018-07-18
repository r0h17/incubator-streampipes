import { Component, EventEmitter, Input, Output } from '@angular/core';
import { StreampipesPeContainer } from "../shared/streampipes-pe-container.model";
import { StreampipesPeContainerConifgs } from "../shared/streampipes-pe-container-configs";
@Component({
    selector: 'consul-service',
    templateUrl: './consul-service.component.html',
    styleUrls: ['./consul-service.component.css']
})
export class ConsulServiceComponent {

    @Input() consulService: StreampipesPeContainer;
    @Output() updateConsulService: EventEmitter<StreampipesPeContainer> = new EventEmitter<StreampipesPeContainer>();
    showConfiguration: boolean = false;

    constructor() {
        
    }

    toggleConfiguration(): void {
        this.showConfiguration = !this.showConfiguration;

    }

    updateConfiguration(): void {
        this.updateConsulService.emit(this.consulService);
    }

}
