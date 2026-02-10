package com.briano.meeting_room_booking_system.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.briano.meeting_room_booking_system.dto.EquipmentDTO;
import com.briano.meeting_room_booking_system.entity.Equipment;
import com.briano.meeting_room_booking_system.exception.DuplicateEquipmentException;
import com.briano.meeting_room_booking_system.exception.EquipmentNotFoundException;
import com.briano.meeting_room_booking_system.mapper.EquipmentMapper;
import com.briano.meeting_room_booking_system.repository.EquipmentRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EquipmentServiceImpl implements EquipmentService{
	
	private final EquipmentRepository equipmentRepository;
	private final EquipmentMapper equipmentMapper;
	
	@Override
	@Transactional
	public EquipmentDTO createEquipment(EquipmentDTO equipmentDTO) {
		log.info("Creating a new equipment: {} " , equipmentDTO.getName());
		if (this.equipmentRepository.existsByName(equipmentDTO.getName())) {
			log.error("Equipment already exists: {}", equipmentDTO.getName());
			throw new DuplicateEquipmentException(equipmentDTO.getName());
		}
		
		Equipment equipment = this.equipmentMapper.toEquipment(equipmentDTO);
		Equipment savedEquipment = this.equipmentRepository.save(equipment);
		
		log.info("Equipment created with success - ID: {}", savedEquipment.getId());
		
		return this.equipmentMapper.toEquipmentDTO(savedEquipment);
	}
	
	@Override
	public EquipmentDTO getEquipmentById(Long id) {
		log.info("Fetching the equipment with ID - {}", id);
		
		Equipment equipment = this.equipmentRepository.findById(id)
				.orElseThrow(() -> new EquipmentNotFoundException(id));
		
		return this.equipmentMapper.toEquipmentDTO(equipment);
	}
	
	@Override
	public List<EquipmentDTO> getAllEquipments() {
		log.info("Fetching all equipments...");
		
		List<Equipment> equipments = this.equipmentRepository.findAll();
		return this.equipmentMapper.toEquipmentDTOs(equipments);
	}
	
	@Override
	@Transactional
	public EquipmentDTO updateEquipment(Long id, EquipmentDTO equipmentDTO) {
		log.info("Updating the equipment with ID - {}", id);
		
		Equipment existingEquipment = this.equipmentRepository.findById(id)
				.orElseThrow(() -> new EquipmentNotFoundException(id));
		
		this.equipmentMapper.updateEntityFromDTO(equipmentDTO, existingEquipment);
		Equipment updateEquipment = this.equipmentRepository.save(existingEquipment);
		
		return this.equipmentMapper.toEquipmentDTO(updateEquipment);
	}
	
	@Override
	@Transactional
	public void deleteEquipment(Long id) {
		log.info("Deleting equipment with ID - {} in process", id);
		
		Equipment existingEquipment = this.equipmentRepository.findById(id)
				.orElseThrow(()-> new EquipmentNotFoundException(id));
		
		this.equipmentRepository.delete(existingEquipment);
		
		log.info("Equipment deleted with success...");
	}

}
